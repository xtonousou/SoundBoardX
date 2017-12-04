#! /usr/bin/env bash
find . -name '*.png' | xargs optipng -nc -nb -o7 -full
