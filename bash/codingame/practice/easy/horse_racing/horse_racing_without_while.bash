# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

read N
declare -a strengths
declare -a sorted
declare -a reversed

for (( i=0; i<N; i++ )); do
    read strengths["$i"]

done

IFS=$'\n' reversed=($(sort -n -r <<<"${strengths[*]}"))

duplicated=$(sed -r 's/ ([0-9]+)/ \1 \1/g' <<<${reversed[*]})
duplicated=$(sed -r 's/([0-9]+)$//g' <<<${duplicated[*]})
duplicated=$(sed -r 's/([0-9]+) ([0-9]+)/$((\1 - \2))/g' <<<${duplicated[*]})
echo "duplicated : $duplicated" >&2
differences=$(eval echo "$duplicated" | tr " " "\n" | sort -n | uniq | head -n 1 | tr "\n" " " | tr -d '[:space:]')
echo "differences : $differences" >&2
echo "$differences"
