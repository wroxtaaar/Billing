Fancy Steel Invoice - installable web app (PWA)

Files: index.html, manifest.webmanifest, sw.js, icons/ (3 PNGs)

1. Upload the whole folder to any HTTPS static host, keeping the structure:
   - Netlify: app.netlify.com/drop  (drag the folder)
   - GitHub Pages, Cloudflare Pages, Firebase Hosting also work
   (Opening index.html straight from the phone's storage will NOT install as an app; it must be on https.)
2. Open the URL in Chrome on Android -> menu -> "Install app" / "Add to Home screen".
3. Works offline after the first visit. Data is saved in that browser on that phone.
To ship an update, upload the new files and bump CACHE in sw.js (fsw-invoice-v2).
