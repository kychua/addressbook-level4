#!/usr/bin/env bash
#
# Pushes asciidoctor generated files to gh-pages branch.

set -e # exit with nonzero exit code if any line fails

if [[ "true" = "$TRAVIS_PULL_REQUEST" || "master" != "$TRAVIS_BRANCH" ]]; then
  echo "Not a commit to master branch. Not deploying to GitHub Pages." >&2
  exit 0
fi

GITHUB_REPO="kychua/addressbook-level4.git"
commit_sha=$(git rev-parse --short HEAD)

cd build/docs/html5

git init
git config user.name "Travis"
git config user.email "travis@travis-ci.org"

git remote add upstream "https://${GITHUB_TOKEN}@github.com/${GITHUB_REPO}"
git fetch upstream
git reset upstream/gh-pages

git add -A .
git commit -m "Rebuild pages at ${commit_sha}"
git push -q upstream HEAD:gh-pages
