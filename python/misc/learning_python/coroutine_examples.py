import asyncio
import time


async def simple_asynchronous_hello():
    print(f"started at {time.strftime('%X')} in simple_asynchronous_hello")
    print('hello')
    await asyncio.sleep(1)
    print('world')
    print(f"finished at {time.strftime('%X')} in simple_asynchronous_hello")


async def say_after(delay, what):
    await asyncio.sleep(delay)
    print(what)


async def asynchronous_hello():
    print(f"started at {time.strftime('%X')} in asynchronous_hello")
    await say_after(1, 'hello')
    await say_after(2, 'world')
    print(f"finished at {time.strftime('%X')} in asynchronous_hello")

async def concurrent_asynchronous_hello():
    hello_task = asyncio.create_task(
        say_after(1, 'hello'))

    world_task = asyncio.create_task(
        say_after(2, 'world'))

    print(f"started at {time.strftime('%X')}")

    await hello_task
    await world_task

    print(f"finished at {time.strftime('%X')}")

if __name__ == "__main__":
    asyncio.run(simple_asynchronous_hello())
    asyncio.run(asynchronous_hello())
    asyncio.run(concurrent_asynchronous_hello())
