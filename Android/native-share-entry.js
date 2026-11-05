import { Filesystem, Directory } from "@capacitor/filesystem";
import { Share } from "@capacitor/share";

window.__nativeInvoiceShare = async (blob, filename) => {
  const dataUrl = await new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.onerror = () => reject(reader.error || new Error("Could not read invoice image"));
    reader.onload = () => resolve(reader.result);
    reader.readAsDataURL(blob);
  });
  const base64 = String(dataUrl).split(",")[1];
  const saved = await Filesystem.writeFile({
    path: filename,
    data: base64,
    directory: Directory.Cache,
    recursive: true
  });
  await Share.share({
    title: filename,
    text: "Fancy Steel Works invoice",
    files: [saved.uri],
    dialogTitle: "Save or share invoice image"
  });
};
