T = readtable('Heap_odwrocone.csv');
Y = T.Var1;
X = T.Var2;
plot(X,Y)
title('Heap, odwrocone')
xlabel('liczba elementów')
ylabel('zmierzony czas')
ylim([0 max(Y)])
xlim([0 max(X)])

