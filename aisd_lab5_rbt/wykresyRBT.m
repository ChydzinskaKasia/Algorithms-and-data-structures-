plot(measureAtoZ.(1),measureAtoZ.(2),'DisplayName','measureAtoZ.(2)','LineWidth',5);
hold on;
plot(measureAtoZ.(1),measureAtoZ.(3),'DisplayName','measureAtoZ.(3)','color','m','LineWidth',3);
plot(measureAtoZ.(1),measureAtoZ.Rand,'DisplayName','measureAtoZ.Rand','color','g','LineWidth',2);
legend('A do Z','Z do A','losowo')
title('Liczba rekurencyjnych wywołań wstawiania elementów do drzewa RBT')
xlabel('liczba elementów')
ylabel('liczba wywołań metody put')
hold off;

