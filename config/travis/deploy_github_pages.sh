#!/bin/sh
# Pushes files generated by Asciidoctor and associated files to gh-pages branch for commits to master branch.

set -o errexit # exit with nonzero exit code if any line fails

if [ -z "$GITHUB_TOKE" ]; then
  echo 'GITHUB_TOKEN is not set up in Travis. Skipping deploy.'
  exit 0
fi;

set -o nounset # exit if variable is unset

# Pull requests and commits to other branches should not try to deploy.
# if [ "false" != "$TRAVIS_PULL_REQUEST" -o "master" != "$TRAVIS_BRANCH" ]; then
#   echo "Not a commit to master branch. Skipping deploy to GitHub Pages."
#   exit 0
# fi

cd build/docs/html5

git init
git config user.name 'Travis'
git config user.email 'travis@travis-ci.org'

git config credential.helper 'store --file=.git/credentials'
echo "https://${GITHUB_TOKEN}:@github.com" > .git/credentials

git remote add upstream "https://github.com/${TRAVIS_REPO_SLUG}.git"

# Reset to gh-pages branch, or create orphan branch if gh-pages does not exist in remote.
if git ls-remote --exit-code --heads upstream gh-pages; then
    git fetch --depth=1 upstream gh-pages
    git reset upstream/gh-pages
elif [ $? -eq 2 ]; then # exit code of git ls-remote is 2 if branch does not exist
    git checkout --orphan gh-pages
else # error occurred
    exit $?
fi

# Exit if there are no changes to gh-pages files.
if changes=$(git status --porcelain) && [ -z "$changes" ]; then
    echo 'No changes to GitHub Pages files; exiting.'
    exit 0
fi

git add -A .
git commit -m "Rebuild pages at ${TRAVIS_COMMIT}"
git push --quiet upstream HEAD:gh-pages
