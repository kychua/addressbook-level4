#!/bin/bash

rev=$(git rev-parse --short HEAD)

cd "$TRAVIS_BUILD_DIR"
cd build/docs/html5

git init
git config user.name "Travis"
git config user.email "Travis"

git remote add upstream "https://${GITHUB_API_KEY}@github.com/kychua/addressbook-level4.git"
git fetch upstream
git reset upstream/gh-pages

touch .

git add -A .
git commit -m "Rebuild pages at ${rev}"
git push -q upstream HEAD:gh-pages
