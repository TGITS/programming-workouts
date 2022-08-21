#[derive(Debug, PartialEq, Eq)]
pub enum Classification {
    Abundant,
    Perfect,
    Deficient,
}

pub fn classify(num: u64) -> Option<Classification> {
    if num == 0 {
        return None;
    }

    let mut sum: u64 = 0;
    for n in 1..num {
        if num % n == 0 {
            sum += n;
        }
    }

    if sum == num {
        return Some(Classification::Perfect);
    } else if sum > num {
        return Some(Classification::Abundant);
    } else if sum < num {
        return Some(Classification::Deficient);
    } else {
        return None;
    }
}
