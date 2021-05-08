@app
trying-arc

# this ensures all compiled JS code is available
@shared
src out/cljs-runtime

@http
get /
get /nanoid
get /random

# @aws
# profile default
# region us-west-1
  