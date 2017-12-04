#! /usr/bin/env bash

echo "Creating directories ..."

[[ ! -d "mp3/" ]] && mkdir mp3

for f in *.mp4; do
  echo "Checking ${f} ..."
  [[ -d "${f}" ]] && continue
  echo "Converting to '${f}.mp3' ..."
  ffmpeg -i "${f}" "mp3/${f}.mp3"
done

echo "Files are located in 'mp3/' directory."
