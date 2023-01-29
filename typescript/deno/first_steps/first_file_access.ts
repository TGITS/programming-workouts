// run it with for example :
// deno run --allow-read first_file_access.ts "C:\Windows\System32\Drivers\etc\hosts"

const filenames = Deno.args;
for (const filename of filenames) {
  const file = await Deno.open(filename);
  await file.readable.pipeTo(Deno.stdout.writable);
}
