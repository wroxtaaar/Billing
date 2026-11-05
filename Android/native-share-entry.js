import { registerPlugin } from "@capacitor/core";

const InvoiceSaver = registerPlugin("InvoiceSaver");

window.__nativeInvoiceSave = async (blob, filename) => {
  const dataUrl = await new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.onerror = () => reject(reader.error || new Error("Could not read invoice image"));
    reader.onload = () => resolve(reader.result);
    reader.readAsDataURL(blob);
  });
  const data = String(dataUrl).split(",")[1];
  return InvoiceSaver.savePng({ filename, data });
};
