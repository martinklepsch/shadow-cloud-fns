#!/usr/bin/env bash

npx shadow-cljs release get-nanoid get-random get-another-nanoid

for FUNCTION_DIR in src/http/*
do
  mv $FUNCTION_DIR/index.js $FUNCTION_DIR/input.js
  ncc build $FUNCTION_DIR/input.js -o $FUNCTION_DIR
done

for FUNCTION_DIR in src/http/*
do
  rm -rf $FUNCTION_DIR/node_modules/@architect
  rm $FUNCTION_DIR/index.js.map
  rm $FUNCTION_DIR/input.js
done
