Fancy Steel Works - Invoice Maker (PWA)

Run locally:
- Open this folder with any static web server.
- For example: python3 -m http.server 8000
- Visit http://localhost:8000
- Do not open index.html using file:// if you want PWA installation/offline support.

Deploy on Render:
- This is a static site; no backend, Node install, or build step is required.
- Repository: https://github.com/wroxtaaar/Billing
- Branch: main
- Publish directory: WebApp
- Build command: echo "No build step required"
- The root render.yaml contains the Render Blueprint configuration.

Install on Android:
- Open the HTTPS site in Chrome.
- Use the browser menu -> Install app / Add to Home screen.
- The service worker caches the app shell for offline use after the first visit.

Data and privacy:
- Bills, customers, and products are stored in the browser's localStorage on that device/browser.
- Data is not uploaded to a server and does not automatically sync across devices or browsers.
- Use the built-in backup/export controls to move data to another device.
