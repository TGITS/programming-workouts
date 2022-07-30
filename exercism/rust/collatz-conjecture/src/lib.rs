pub fn collatz(n: u64) -> Option<u64> {
    if n == 0 {
        return None;
    }

    let mut step: u64 = 0;
    let mut temp: u64 = n;
    let mut overflow = false;
    while temp > 1 && !overflow {
        if temp % 2 == 0 {
            temp = temp / 2;
        } else {
            (temp, overflow) = temp.overflowing_mul(3);
            if !overflow {
                (temp, overflow) = temp.overflowing_add(1);
            }
        }
        step = step + 1
    }

    if overflow {
        return None;
    }
    Some(step)
}
