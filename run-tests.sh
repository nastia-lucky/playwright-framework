#!/bin/sh

echo "====================================="
echo "Starting Playwright Tests"
echo "====================================="

exec mvn test "$@"