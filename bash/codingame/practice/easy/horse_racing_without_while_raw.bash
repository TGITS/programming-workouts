# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

read N
declare -a strengths
declare -a sorted
declare -a reversed

function search_minimal_difference {
    declare -i index=$1
    declare -i length=$2
    declare -i minimal_difference=$3
    declare -i lower_bound=$4
    while (( "$index" > lower_bound )) && (( "$minimal_difference" > 0))
    do
        ((difference=sorted["$index"]-sorted[((index-1))]))
        (( "$difference" < "$minimal_difference" )) && minimal_difference="$difference"
        ((difference=sorted[((length-index+1))]-sorted[((length - index))]))
        (( "$difference" < "$minimal_difference" )) && minimal_difference="$difference"
        ((index--))
    done
    echo "$minimal_difference"
}

for (( i=0; i<N; i++ )); do
    read strengths["$i"]
    #read n
    #strengths["$n"]=$n
done

#sorted=(${strengths[*]})
IFS=$'\n' sorted=($(sort -n <<<"${strengths[*]}"))
echo "sorted array : ${sorted[*]}" >&2
IFS=$'\n' reversed=($(sort -n -r <<<"${sorted[*]}"))
#duplicated=$(echo "${reversed[*]}" | sed -r 's/ ([0-9]+) / \1 \1 /g')
duplicated=$(sed -r 's/ ([0-9]+)/ \1 \1/g' <<<${reversed[*]})
echo "duplicated : $duplicated" >&2
duplicated=$(sed -r 's/([0-9]+)$//g' <<<${duplicated[*]})
echo "duplicated : $duplicated" >&2
duplicated=$(sed -r 's/([0-9]+) ([0-9]+)/$((\1 - \2))/g' <<<${duplicated[*]})
echo "duplicated : $duplicated" >&2
#duplicated=$(sed -r 's/^\$\(/\"\$\(/g' <<<${duplicated[*]})
#echo "duplicated : $duplicated" >&2
#duplicated=$(sed -r 's/\) $/\)\"/g' <<<${duplicated[*]})
#echo "duplicated : $duplicated" >&2
#declare -i index=${#strengths[@]}-1
declare -i index=${#sorted[@]}-1
declare -i length=$index
#IFS=$'\n' sorted=($(sort -n <<<"${strengths[*]}"))
echo "index : $index" >&2
echo "length : $length" >&2
((lower_bound=length/2))
#differences=$(echo "$duplicated" | cut -d : -f1- | sed -r 's/://g')
differences=$(eval echo "$duplicated" | tr " " "\n" | sort -n | uniq | head -n 1 | tr "\n" " " | tr -d '[:space:]')
echo "differences : $differences" >&2
#echo 'dd aa cc bb' | tr " " "\n" | sort | tr "\n" " "
#((value=$differences))
echo "value : $value" >&2
#echo "$duplicated" | cut -d : -f1- | sed -r 's/:/\n/g' | echo | sort -n | head -n 1
#((minimal_difference=sorted["$index"]-sorted[((--index))]))
#((index-=1))

#while (( "$index" > 0 )) && (( "$minimal_difference" > 0))
#do
#    ((difference=sorted["$index"]-sorted[((--index))]))
#    (( "$difference" < "$minimal_difference" )) && minimal_difference="$difference"
#done

#minimal_difference=`search_minimal_difference "$index" "$length" "$minimal_difference" "$lower_bound"`

#echo "$minimal_difference"
echo "$differences"
