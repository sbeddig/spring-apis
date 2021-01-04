#!/usr/bin/env bash

set -euo pipefail

docker rm -f "$(docker container ls --format="{{.ID}}\t{{.Ports}}" | grep 27017 | awk '{print $1}')" > /dev/null