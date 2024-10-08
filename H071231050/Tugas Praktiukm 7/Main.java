package TugasPraktiukm7;

import TugasPraktiukm7.Models.Karyawan;
import TugasPraktiukm7.Models.Pendidikan;
import TugasPraktiukm7.Models.Pengalaman;
import TugasPraktiukm7.Models.Projek;
import TugasPraktiukm7.Utils.util;
import TugasPraktiukm7.Utils.sound;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Karyawan> data = new ArrayList<>();
        boolean stop = false;
        while (!stop) {
            Scanner sc = new Scanner(System.in);
            util.dataKaryawan();
            String pilihan = sc.nextLine();
            switch (pilihan) {
                case "a":
                    Karyawan karyawan = new Karyawan();
                    boolean valid = false;
                    while (!valid) {
                        try {
                            util.nama();
                            karyawan.setName(sc.nextLine());
                            util.umur();
                            karyawan.setAge(sc.nextInt());
                            sc.nextLine();
                            valid = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Inputan tidak valid");
                            sc.nextLine();
                        }
                    }
                    Pengalaman pengalaman = new Pengalaman(karyawan.getName(), karyawan.getAge());
                    pengalaman.status();
                    pengalaman.pengalamanKerja();

                    Pendidikan pendidikan = new Pendidikan();
                    pendidikan.pendidikanKaryawan();
                    
                    Projek projek = new Projek();
                    projek.jumlahProjek();

                    if (pendidikan.getNilaiPlus() != 1 || projek.getNilaiPlus() != 1 || pengalaman.getAge() < 18
                            || pengalaman.getNilaiPlus() != 1) {
                        util.karyawanDitolak();
                        sound.Accept("/Users/ervinhsn/Documents/JavaProjects/Sound/violin-lose-4-185125.wav");
                    } else {
                        util.sedangProses();
                        sound.Accept("/Users/ervinhsn/Documents/JavaProjects/Sound/tadaa-47995.wav");
                        pendidikan.prosesKehidupan();
                        projek.prosesKehidupan();
                        util.batas();
                        util.karyawanDiterima();
                        util.batas();
                        data.add(pengalaman);

                    }
                    break;
                case "b":
                    for (Karyawan hasil : data) {
                        hasil.displayInfo();
                    }
                    break;
                case "c":
                    cekGaji(data);
                    break;
                case "d":
                    stop = true;
                    util.selamatTinggal();
                    sc.close();
                    break;
                default:
                    System.out.println("Inputan Hanya a-c");
                    break;
            }
        }

    }

    public static void cekGaji(ArrayList<Karyawan> data) {
        // ArrayList<Karyawan> data = new ArrayList<>() ;
        String orang = "";
        int gajiTerbesar = 0;

        if (data.isEmpty()) {
            System.out.println("Tidak ada data karyawan.");
            return;
        }

        for (Karyawan gaji : data) {
            if (gaji.getSalary() > gajiTerbesar) {
                gajiTerbesar = gaji.getSalary();
                orang = gaji.getName();
            } else {

            }
        }
        System.out.println("Gaji tertinggi di pegang oleh : ");
        System.out.println("Nama : " + orang);
        System.out.println("Gaji : " + gajiTerbesar);

    }
}
