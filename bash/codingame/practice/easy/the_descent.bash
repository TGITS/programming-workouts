while true; do
    ((max_height=0))
    ((max_height_index=0))
    for (( i=0; i<8; i++ )); do
        # mountainH: represents the height of one mountain.
        read mountainH
        if (($max_height<$mountainH))
        then
            ((max_height=$mountainH))
            ((max_height_index=$i))
        fi
        #echo "i : $i ; mountainH : $mountainH ; max_height : $max_height ; max_height_index : $max_height_index" >&2
    done

    # Write an action using echo
    # To debug: echo "Debug messages..." >&2

    echo "$max_height_index" # The index of the mountain to fire on.
done
