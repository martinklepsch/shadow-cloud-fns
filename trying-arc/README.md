# why is this interesting

- packaging clojurescript code for cloud function environments
  - advanced build, npm dependencies, light
  - `ncc` to package
- using architect for infrastructure automatino
  - some useful conventions but not expecting usage of specific tools etc

# Common Pitfalls

```
        <p>Process exited with 1<p>
        <pre></pre>
        <pre>TypeError: Cannot read property 'constructor' of undefined
```

The `exports` var for each function needs to have a key `handler`


## @architect/shared in `src` - any bad sideeffects?

Solved by moving ClojureScript files to `src/cljs`

## adding a new function

1. Add new entry to shadow-cljs.edn that emits file to correct directory for architect
1. Add entry to `app.arc`
1. Generate namespace with correct exports
   - or add to existing?


## Sandbox demo

`shadow-cljs watch get-nanoid get-another-nanoid get-random` `arc sandbox`

```
curl localhost:3333/nanoid
curl localhost:3333/another-nanoid
curl localhost:3333/random
```

## hydration

### ncc

due to architect's hydration approach I looked at other options, `ncc` seemed
particularly promising. The `build.sh` file will create release artifacts and package
them using `ncc`. Combining this with advanced compilation creates tiny lambda
artifacts (the nanoid ones are 23kb zipped).

- [ ] `ncc` probably breaks the source maps that shadow-cljs generated

![](https://p22.tr4.n0.cdn.getcloudapp.com/items/xQubmo66/b421c5f4-6a0d-4d71-85c1-cf9f0da7a271.png?v=9f61420cafb2bb1a4173d8b1d7e22737)

### architect 

[architect's hydration](https://github.com/architect/hydrate/) adds

- node_modules and 
- everything in @shared, **this includes files that are only required for development**

one way around this:

```sh
# run hydration
arc deploy --dry-run

# fish-specific: delete shared dirs + source map files before actual deploy
for p in src/http/*; rm -rf $p/node_modules/@architect $p/index.js.map; end

# deploy without hydrating again
arc deploy --no-hydrate
```
now node_modules is included in the package but the development files are not.
It would be nice if shared stuff could be excluded from hydration somehow. Even
the programmatic API for `architect/hydrate` seems to always call shared().

See `build-arc-hydrate.sh` for a script to do this and to deploy

![](https://p22.tr4.n0.cdn.getcloudapp.com/items/WnuY98xy/c87c67f4-0284-4906-a635-5e6a3616a05e.png?source=viewer&v=b4400660830b644822c3202ce1677ca6)
