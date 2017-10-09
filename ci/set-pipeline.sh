#!/bin/sh
fly -t home sp -p reactive-github-client \
    -c `dirname $0`/pipeline.yml \
    -l `dirname $0`/credentials.yml