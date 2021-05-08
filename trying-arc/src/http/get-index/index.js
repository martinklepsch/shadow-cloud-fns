// learn more about HTTP functions here: https://arc.codes/primitives/http
exports.handler = async function http (req) {
  return {
    statusCode: 200,
    body: "index page 123"
  }
}

exports.handler = function http (req, context, callback) {
	callback(null,
  {
    statusCode: 200,
    headers: {
      'cache-control': 'no-cache, no-store, must-revalidate, max-age=0, s-maxage=0',
      'content-type': 'text/html; charset=utf8'
    },
    body: "index page 345"
  })
}