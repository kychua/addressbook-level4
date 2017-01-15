#!/bin/bash

cd "$TRAVIS_BUILD_DIR"
cd build/docs/html5

git init
git checkout -b gh-pages
git add .
git -c user.name='travis' -c user.email='travis' commit -m init
git push --force --quiet "https://${GITHUB_API_KEY}@$github.com/kychua/addressbook-level4.git" master:gh-pages > /dev/null 2>&1
cd $TRAVIS_BUILD_DIR
