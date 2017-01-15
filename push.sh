#!/bin/bash

set -o errexit -o nounset

if [ "$TRAVIS_BRANCH" != "master" ]
then
  echo "This commit was made against the $TRAVIS_BRANCH and not the master! No deploy!"
  exit 0
fi

rev=$(git rev-parse --short HEAD)

cd build/docs/html5

git init
git config user.name "Travis CI"
git config user.email "travis@travis-ci.org"

git remote add upstream https://${GH_TOKEN}@github.com/kychua/addressbook-level4.git > /dev/null 2>&1
git fetch upstream
git reset upstream/gh-pages

touch .

git add -A .
git commit -m "Rebuild pages at ${rev}"
git push -q upstream HEAD:gh-pages
