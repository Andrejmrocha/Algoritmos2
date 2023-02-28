package uni7;

public class HugeInteger {
	private final int[] number;

	public HugeInteger(String n) {
		this.number = new int[n.length()];

		for (int i = 0, j = number.length - 1; i < number.length; i++, j--) {
			char c = n.charAt(j);

			number[i] = Character.digit(c, 10);
		}
	}

	public HugeInteger(int[] n) {
		this.number = revert(n);
	}

	/**
	 * Realiza a subtração (números naturais)
	 * 
	 * @param other O segundo operando da subtração
	 * @return Um novo HugeNumber resultado da subtração
	 */
	public HugeInteger minus(HugeInteger other) {
		// TODO: Implementar a subtração
		int[] max = null;
		int[] min = null;
		
		if(this.compare(other) == -1) {
			max = this.number;
			min = other.number;
		} else {
			if(this.compare(other) == 1) {
				max = other.number;
				min = this.number;
			} else {
				max = this.number;
				min = other.number;
			}
		}
		
		
		int result[] = new int[max.length];
		int vaiUm = 0;
		
		if(max.length > min.length) {
			int newMin[] = new int[max.length];
			for(int i = 0; i < min.length; i++) {
				newMin[i] = min[i];
			}
			
			for(int j = min.length; j < max.length; j++) {
				newMin[j] = 0;
			}
			for(int i = 0; i< max.length; i++) {
				if(vaiUm == 0) {
					if(max[i] - newMin[i] < 0) {
						result[i] = 10 + (max[i] - newMin[i]);
						vaiUm = 1;
					} else {
						result[i] = max[i] - newMin[i];
					}
				} else {
					if(max[i] - newMin[i] < 0) {
						result[i] = 10 + max[i] - newMin[i] - 1;
						vaiUm = 1;
					} else {
						result[i] = max[i] - newMin[i] - 1;
						vaiUm = 0;
					}
				}
			}
			
		} else {
			for(int i = 0; i< max.length; i++) {
				if(vaiUm == 0) {
					if(max[i] - min[i] < 0) {
						result[i] = 10 + (max[i] - min[i]);
						vaiUm = 1;
					} else {
						result[i] = max[i] - min[i];
					}
				} else {
					if(max[i] - min[i] < 0) {
						result[i] = 10 + max[i] - min[i] - 1;
						vaiUm = 1;
					} else {
						result[i] = max[i] - min[i] - 1;
						vaiUm = 0;
					}
				}
			}
		}
		
		
		result = revert(result);

		return new HugeInteger(result);
	
	}

	/**
	 * Compara o número passado com o número atual
	 * 
	 * @param other O outro número
	 * @return -1 caso o número atual seja maior, 0 caso os números sejam iguais e
	 *         +1 caso o número passado seja maior.
	 */
	public int compare(HugeInteger other) {
		// TODO: Implementar a comparação
//		String current = this.toString();
//		int newCurrent = Integer.parseInt(current);
//		String current2 = other.toString();
//		int newCurrent2 = Integer.parseInt(current2);
//		if(newCurrent > newCurrent2) {
//			return -1;
//		} else {
//			if(newCurrent == newCurrent2) {
//				return 0;
//			} else {
//				return 1;
//			}
//		}
		
		if(revert(this.number).length > revert(other.number).length) {
			return -1;
		} else {
			if(revert(this.number).length < revert(other.number).length) {
				return 1;
			} else {
				for(int i = 0; i < this.number.length; i++) {
					if(revert(this.number)[i] > revert(other.number)[i]) {
						return -1;
					} else {
						if(revert(this.number)[i] < revert(other.number)[i]) {
							return 1;
						}
					}
				}
				return 0;
			}
		}
	}

	public HugeInteger plus(HugeInteger other) {
		int[] max = null;
		int[] min = null;

		if (this.number.length > other.number.length) {
			max = this.number;
			min = other.number;
		} else {
			max = other.number;
			min = this.number;
		}

		int[] result = new int[max.length + 1];

		int vaiUm = 0;
		for (int i = 0; i < min.length; i++) {
			if ((max[i] + min[i] + vaiUm) >= 10) {
				result[i] = (max[i] + min[i] + vaiUm) - 10;
				vaiUm = 1;
			} else {
				result[i] = (max[i] + min[i] + vaiUm);
				vaiUm = 0;
			}
		}

		for (int i = min.length; i < max.length; i++) {
			if ((max[i] + vaiUm) >= 10) {
				result[i] = max[i] + vaiUm - 10;
				vaiUm = 1;
			} else {
				result[i] = max[i] + vaiUm;
				vaiUm = 0;
			}
		}

		result[result.length - 1] = vaiUm;

		result = revert(result);

		return new HugeInteger(result);
	}
	
	
	private int[] revert(int[] array) {
		int[] result = new int[array.length];

		for (int i = 0, j = array.length - 1; i < result.length; i++, j--) {
			result[i] = array[j];
		}

		return result;
	}

	public String toString() {
		StringBuilder buffer = new StringBuilder();

		int[] copy = revert(number);

		int i = 0;
		if (copy[0] == 0) {
			i = 1;
		}

		for (; i < copy.length; i++) {
			int n = copy[i];
			buffer.append(n);
		}

		return buffer.toString();
	}
}