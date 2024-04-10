import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Start {
  public static void main(String[] args) {
    long startTime = System.nanoTime();
    AtomicBoolean running = new AtomicBoolean(true);

    Thread timeThread = new Thread(() -> {
      while (running.get()) {
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000_000; // Gesamtdauer in Sekunden

        long durationHours = duration / 3600; // Stunden berechnen
        long remainderAfterHours = duration % 3600; // Restliche Sekunden nach der Stundenberechnung
        long durationMinutes = remainderAfterHours / 60; // Minuten aus dem Rest berechnen
        long durationSeconds = remainderAfterHours % 60; // Verbleibende Sekunden berechnen

        System.out.printf("\rDie vergangene Zeit: %d Sekunden total. Das wären %d Stunden, %d Minuten, %d Sekunden.   ",
            duration, durationHours, durationMinutes, durationSeconds);
        try {
          Thread.sleep(1000); // Aktualisieren Sie die Zeit alle 1 Sekunde
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    });

    timeThread.start();

    Scanner sc = new Scanner(System.in);
    String quit;
    do {
      System.out.print("\nMessung beenden. [Q]");
      quit = sc.nextLine().toLowerCase(Locale.ROOT);
    } while (!quit.equals("q"));

    running.set(false);
    sc.close();
  }
}