#include <stdio.h>  
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>
void nhapDuLieu(int **mang, int *n) { //Ham nhap du lieu tu ban phim 
	printf("Hay nhap so phan tu trong mang: ");
	scanf("%d",n);
	if (*n <= 0) {
        printf("So phan tu trong mang phai lon hon 0.\n");
        return;  
    }
	int *new_mang = (int*)realloc(*mang, (*n) * sizeof(int));
    if (new_mang == NULL) {
        printf("Loi khi cap phat bo nho!\n");
        return;
    }
    *mang = new_mang;  

	for(int i = 0; i < *n; i++) {
		printf("a[%d]:",i);
		scanf("%d",&(*mang)[i]);
            }
	printf("\n");
}
void hienThiDuLieu(int *mang, int n) { // ham in du lieu tu ban phim 
	printf("Prime number: ");
        for (int i = 0; i < n; i++) {
            printf("%d ", mang[i]);
        }
        printf("\n");
} 

int palind(int n) {
	int res = 0, ori = 0;
	if (n <= 0) return 0;
	ori = n;
	for(;n > 0; n /= 10) {
	res = res * 10 + (n % 10);
}	
	return res == ori;
}
long calculateBonus(long sales) {
	long salary = 0;
      if(sales < 30000){
         salary = 800;
      } else if(sales >= 30000 && sales < 40000 ){
      	 salary = 800 + (sales - 30000) * 0.01;
      }	else if(sales >= 40000 && sales < 50000  ){
      	  salary = 800 +  (40000 - 30000) * 0.01 + (sales - 40000) * 0.02;
      } else {
      	salary = 800 + ( (40000 - 30000) * 0.01 )+ ((50000 - 40000) * 0.02) + (sales - 50000) * 0.03;
      } 
 return salary;
 }
double pown(int n) {
	double m = 0;
	for (int i = 1; i <= n; i++) {
		m += pow(i,2);
		}
		return m;
	}
int sum(int n) {
	int sum = 0;
	for(int i = 1; i <= n; i++) {
		sum += i;
		}
	return sum;
	}
double calculateS(int n) { //Bai 3
	if (n == 1) { 
		return 1;
	} 
	double S; 
	for(int i = 1; i <= n; i++) {
		S += (double)pown(i)/sum(i);
	}
	return S;
}
int isPrime(int n) {
if (n < 2) return 0; 
    if (n == 2) return 1; 
    if (n % 2 == 0) return 0; 
    for (int i = 3; i * i <= n; i += 2) { 
        if (n % i == 0) return 0; 
    }
    return 1; 
}
int* mangPrime(int* mang, int n, int *nn ) {

    int* mangPrime = (int*)malloc(sizeof(int));
    *nn = 0;
    for (int i = 0; i < n; i++) {
        if (isPrime(mang[i])) {
            mangPrime[(*nn)++] = mang[i];
        }
    }
 mangPrime=(int*)realloc(mangPrime,(*nn)* sizeof(int)); 
    return mangPrime;
}
void menu() {
	int ch, n,nn, result;
		int *mangprime;
	long doanhso,salary;
	double S;
	int *mang = NULL;
	char *chuoi = NULL;
	printf("MENU\n");
    do{
		printf("1- Check Palindromic number.\n");
		printf("2- Calculate employee income.\n");
		printf("3- Calculate S.\n");
		printf("4- Find prime number in an array.\n");
		printf("0- Thoat.\n");
		printf("Xin hay nhap lua chon:");
		if (scanf("%d", &ch) != 1) { 
            scanf("%*s"); 
            printf("Lua chon khong hop le.\n");
            continue;
            }
        getchar();
		switch (ch) {
		case 1: 
			printf("n = ");
			scanf("%d",&n);
			result = palind(n);
			printf("%d\n",result);
			break;
		case 2 : 
		    do {
				printf("Nhap doanh so cua ban: ");
				scanf("%ld",&doanhso);
		    if(n < 0) printf("doanh so phai lon hon 0.\n");
			}while(n < 0);
			  salary = calculateBonus(doanhso);
			  printf("Luong cua ban la : %ldUSD\n",salary);
			break;
		case 3 :
			do {
			printf("n = ");
			scanf("%d",&n);
			if(n < 0) printf("n phai lon hon 0.\n");
			}while(n < 0);
			S = calculateS(n);
	        printf("%lf\n",S);
			break;
		case 4 : 
			nhapDuLieu(&mang, &n);
 			mangprime = mangPrime(mang, n, &nn);
			hienThiDuLieu(mangprime, nn);
			break;
		default :
			printf("Lua chon khong dung hay nhap lai.");
			break;
	    }
	    
	} while(ch !=0);
	
	if (mang != NULL) {
	    free(mang);
	}
}
int main() {
	menu();
//	int
//	printf("");
//	scanf("",);
return 0;
}

