import os
absolute_path = os.path.dirname(__file__)
curr_dir_name = str(absolute_path).split("/")[-1]
inputs_dir = str(absolute_path).replace(curr_dir_name, "") + "inputs/Day25.txt"

class DecimalCode:
    def define_subtract_number(num:str, power:int) ->int:
        if num == "2": return 2*5**power
        elif num == "1": return 5**power
        elif num == "0": return 0
        elif num == "-": return -1*5**power
        else : return -2*5**power

    def calculate_number(number:str) -> int:
        num = number
        if number[-1] == "\n": num = num [:-1]
        number_length = len(num) -1
        current_num = 0
        for i, char in enumerate(num):
            power = number_length - i
            current_num += DecimalCode.define_subtract_number(char, power)
        return current_num

    def sum_all_numbers_to_decimal():
        with open(inputs_dir, 'r') as f: 
            lines = f.readlines()
            sum = 0
            for line in lines:
                sum += DecimalCode.calculate_number(str(line))
            return sum
    

class SnafuCode:
    def __init__(self, result:int):
        self.result = result
        self.numbers_dict = {
            2 : "2",
            1 : "1",
            0 : "0",
            -1 : "-",
            -2 : "=",
        }
        
        self.exponentiation_powers_of_five =[2,10,50,250,1250,6250,31250,156250,781250,3906250,19531250,97656250,488281250,2441406250,12207031250,61035156250,305175781250,1525878906250,7629394531250,38146972656251] 
        
        
    def calc_max_exp_value(self, value) -> tuple:
        upper_exp = 1
        lower_exp = 0
        lower_limit = 0
        while value >  2*5**lower_exp and value > 2*5**upper_exp:
            lower_limit = 0
            for val in self.exponentiation_powers_of_five[:lower_exp+1]:
                lower_limit += val
            upper_exp += 1
            lower_exp += 1
            print(lower_limit, lower_exp, 2*5**upper_exp, upper_exp,"ll le ul ue")
        return(lower_limit, 2*5**upper_exp)

    def get_snafu_code(self):
        val = self.result
        final_snafu = ""
        count = 19
        while count >= 0:
            print(val)

            min_exp_val, max_exp_val = self.calc_max_exp_value(val)
            print("min",min_exp_val, "max",max_exp_val, "val", val)

            if  val - max_exp_val + min_exp_val >= 0 :
                print("2")
                val = val - max_exp_val + min_exp_val
                final_snafu += self.numbers_dict[2]

            elif  val - max_exp_val/2 + min_exp_val >= 0 :
                print("1")
                val = val - max_exp_val/2 + min_exp_val
                final_snafu += self.numbers_dict[1]

            elif val + ((max_exp_val/2)/5) >= max_exp_val :
                print("-1")
                val = val + max_exp_val/2 + min_exp_val
                final_snafu += self.numbers_dict[1]

            elif val + ((max_exp_val/2)/5)*2 >= max_exp_val :
                print("-2")
                val = val + min_exp_val + min_exp_val
                final_snafu += self.numbers_dict[2]
            else:
                print (val, "val", min_exp_val, "min")
                print("0")
                final_snafu += self.numbers_dict[0]
            
            count -= 1
        return final_snafu

def main():
    decimal_result:int = DecimalCode.sum_all_numbers_to_decimal()
    sc = SnafuCode(decimal_result)
    sc.calc_max_exp_value(decimal_result)

    print(sc.get_snafu_code())


if __name__ == '__main__': main()

  