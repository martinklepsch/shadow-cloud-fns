@app
trying-arc

# this ensures all compiled JS code is available
@shared
src out/http/get-nanoid/cljs-runtime

@http
get /
get /nanoid
get /random

# @aws
# profile default
# region us-west-1
  