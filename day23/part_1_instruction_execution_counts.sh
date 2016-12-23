#!/usr/bin/env bash

cat part_1_executions.txt | cut -f1 -d'|' | sort | uniq -c
