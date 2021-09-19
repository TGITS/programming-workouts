# Example taken from https://realpython.com/async-io-python/ with little modification

import asyncio


async def count(id):
    print(f"{id} - One")
    await asyncio.sleep(1)
    print(f"{id} - Two")


async def main():
    await asyncio.gather(count(1), count(2), count(3))


if __name__ == "__main__":
    import time

    s = time.perf_counter()
    asyncio.run(main())
    elapsed = time.perf_counter() - s
    print(f"{__file__} executed in {elapsed:0.2f} seconds.")
