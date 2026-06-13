# AIGIS IDS Pipeline (Snort + Zeek + ELK + ML Backend)

## Overview

This project is an intrusion detection and analysis pipeline based on:

- Snort 3 (signature-based IDS)
- Zeek 8.0.5 (network security monitoring)
- ELK Stack 8.12.0 (Elasticsearch, Logstash, Kibana)
- ML Service (traffic classification / inference backend)
- Java Backend
- Filebeat 8.12.0
Pipeline flow:

Snort → Filebeat → Logstash → Backend AIGIS →  ML AIGIS → Elasticsearch → Kibana

Zeek → Filebeat → Logstash → Elasticsearch → Backend AIGIS
---

## Architecture

- Snort generates JSON alerts (`alert_json.txt`)
- Zeek produces connection logs (`conn.log`, `local.zeek`)
- Logstash&Filebeat ingests logs and forwards them via HTTP
- Backend processes events and performs ML inference
- Kibana is used for visualization

---

## Requirements

- Docker & Docker Compose
- Linux (Ubuntu recommended)
- Network interface access (for Snort/Zeek)

---

## Quick Start

  Create network

```bash
docker network create ids-net

. Start full stack
docker compose up -d
Snort 3
Run Snort manually
snort -c /usr/local/snort/etc/snort/snort.lua -i [interface]
JSON logging config

Logs are written to:

/var/log/snort/alert_json.txt
Zeek 8.0.5
Run Zeek
/opt/zeek/bin/zeek -C -i [interface] local.zeek
Output logs

Default logs:

/opt/zeek/logs/current/
Services
Elasticsearch
Port: 9200
Stores parsed security events
Kibana
Port: 5601
Visualization dashboard
Logstash
Reads Snort + Zeek logs
Sends data to backend via HTTP
ML Backend (AIGIS)
Port: 8080

Endpoint:

POST /webhook/snort
Logstash Pipeline

Input sources:

Snort JSON log
Zeek logs (optional extension)

Output:

HTTP → backend
Troubleshooting

Check logs:

docker logs -f logstash
docker logs -f elasticsearch
docker logs -f kibana

Verify ELK:

curl http://localhost:9200
Notes
Snort and Zeek require root privileges or elevated capabilities
Ensure correct network interface is selected
Logstash requires file permissions to read /var/log/snort

---

# docker-compose.yml

```yaml
version: "3.9"

networks:
  ids-net:
    driver: bridge

services:

  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:8.12.0
    container_name: elasticsearch
    environment:
      - discovery.type=single-node
      - xpack.security.enabled=false
    ports:
      - "9200:9200"
    networks:
      - ids-net

  kibana:
    image: docker.elastic.co/kibana/kibana:8.12.0
    container_name: kibana
    environment:
      - ELASTICSEARCH_HOSTS=http://elasticsearch:9200
    ports:
      - "5601:5601"
    depends_on:
      - elasticsearch
    networks:
      - ids-net

  logstash:
    image: docker.elastic.co/logstash/logstash:8.12.0
    container_name: logstash
    volumes:
      - ./logstash.conf:/usr/share/logstash/pipeline/logstash.conf
      - /var/log/snort:/var/log/snort
      - ./sincedb:/var/lib/logstash
    depends_on:
      - elasticsearch
    networks:
      - ids-net

  aigis-backend:
    image: aigis-backend:latest
    container_name: aigis
    ports:
      - "8080:8080"
    networks:
      - ids-net
