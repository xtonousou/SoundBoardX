#! /usr/bin/env bash

echo "Creating directories ..."

[[ ! -d "pnm/" ]] && mkdir pnm
[[ ! -d "svg/" ]] && mkdir svg

for f in *.png; do
  echo "Checking ${f} ..."
  [[ -d "${f}" ]] && continue
  file=$(awk -F "." '{print $1}' <<< "${f}")
  echo "Converting to ${file}.svg ..."
  convert "${f}" "pnm/${file}.pnm"
  potrace "pnm/${file}.pnm" -s -o "svg/${file}.svg"
done

echo "Cleaning ..."
rm -rf pnm/
echo "Files are located in 'svg/' directory."
