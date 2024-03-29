def recursive_method(target, coin_array, memo={}):
    if target in memo:
        return memo[target]
    elif target == 0:
        return []
    elif target < 0:
        return None

    current_combination = None

    for coin in coin_array:
        r_combination = recursive_method(target - coin, coin_array, memo)
        if r_combination != None:
            combination = r_combination + [coin]
            if current_combination == None or len(combination) < len(current_combination):
                 current_combination = combination

    memo[target] = current_combination
    return current_combination

a = input().split(" ")
b = input().split(" ")
num_of_coins = int(a[0])
target = int(a[1])
coin_array = []
for i in range(num_of_coins):
    coin_array.append(int(b[i]))

coin_array = recursive_method(target,coin_array)
if coin_array == None:
    print(-1)
else:
    print(len(coin_array))
