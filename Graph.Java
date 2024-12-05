public class Graph {
    private SimpulGaris hubungannya;
    private static class SimpulGaris {
        String kunci;
        DaftarGaris nilai;
        SimpulGaris next;

        SimpulGaris(String kunci, DaftarGaris nilai, SimpulGaris next) {
            this.kunci = kunci;
            this.nilai = nilai;
            this.next = next;
        }
    }
    private static class DaftarGaris {
        NodeGaris head;

        void tambah(Garis data) {
            if (head == null) {
                head = new NodeGaris(data, null);
                return;
            }
            NodeGaris saatIni = head;
            while (saatIni.next != null) {
                saatIni = saatIni.next;
            }
            saatIni.next = new NodeGaris(data, null);
        }
    }

    private static class NodeGaris {
        Garis data;
        NodeGaris next;

        NodeGaris(Garis data, NodeGaris next) {
            this.data = data;
            this.next = next;
        }
    }

    private static class Garis {
        String tujuan;
        int bobot;

        Garis(String tujuan, int bobot) {
            this.tujuan = tujuan;
            this.bobot = bobot;
        }
    }

    private DaftarGaris dapatkanDaftarGaris(String kunci) {
        SimpulGaris saatIni = hubungannya;
        while (saatIni != null) {
            if (saatIni.kunci.equals(kunci)) {
                return saatIni.nilai;
            }
            saatIni = saatIni.next;
        }
        return null;
    }
    private static class EntriInt {
        String kunci;
        int nilai;
        EntriInt next;

        EntriInt(String kunci, int nilai, EntriInt next) {
            this.kunci = kunci;
            this.nilai = nilai;
            this.next = next;
        }
    }
    private static class EntriStr {
        String kunci;
        String nilai;
        EntriStr next;

        EntriStr(String kunci, String nilai, EntriStr next) {
            this.kunci = kunci;
            this.nilai = nilai;
            this.next = next;
        }
    }
    private static class SimpulAntrianPrioritas {
        String nama;
        int jarak;
        SimpulAntrianPrioritas next;

        SimpulAntrianPrioritas(String nama, int jarak, SimpulAntrianPrioritas next) {
            this.nama = nama;
            this.jarak = jarak;
            this.next = next;
        }
    }
    private static class SimpulSet {
        String data;
        SimpulSet next;

        SimpulSet(String data, SimpulSet next) {
            this.data = data;
            this.next = next;
        }
    }
    private static class SimpulTumpukan {
        String data;
        SimpulTumpukan next;

        SimpulTumpukan(String data, SimpulTumpukan next) {
            this.data = data;
            this.next = next;
        }
    }
    private static class SimpulJalur {
        Jalur data;
        SimpulJalur next;

        SimpulJalur(Jalur data, SimpulJalur next) {
            this.data = data;
            this.next = next;
        }
    }

    private static class Jalur {
        String rute;
        int jarak;

        Jalur(String rute, int jarak) {
            this.rute = rute;
            this.jarak = jarak;
        }
    }

    private boolean berisiDalamSet(SimpulSet set, String data) {
        SimpulSet saatIni = set;
        while (saatIni != null) {
            if (saatIni.data.equals(data)) return true;
            saatIni = saatIni.next;
        }
        return false;
    }

    private int dapatkanJarak(EntriInt jarak, String kunci) {
        EntriInt saatIni = jarak;
        while (saatIni != null) {
            if (saatIni.kunci.equals(kunci)) return saatIni.nilai;
            saatIni = saatIni.next;
        }
        return Integer.MAX_VALUE;
    }

    private EntriInt masukkanJarak(EntriInt jarak, String kunci, int nilai) {
        EntriInt saatIni = jarak;
        while (saatIni != null) {
            if (saatIni.kunci.equals(kunci)) {
                saatIni.nilai = nilai;
                return jarak;
            }
            saatIni = saatIni.next;
        }
        jarak = new EntriInt(kunci, nilai, jarak);
        return jarak;
    }

    private EntriStr masukkanSebelumnya(EntriStr sebelumnya, String kunci, String nilai) {
        EntriStr saatIni = sebelumnya;
        while (saatIni != null) {
            if (saatIni.kunci.equals(kunci)) {
                saatIni.nilai = nilai;
                return sebelumnya;
            }
            saatIni = saatIni.next;
        }
        sebelumnya = new EntriStr(kunci, nilai, sebelumnya);
        return sebelumnya;
    }

    private SimpulAntrianPrioritas tambahkanKeAntrianPrioritas(SimpulAntrianPrioritas antrian, String nama, int jarak) {
        if (antrian == null || jarak < antrian.jarak) {
            return new SimpulAntrianPrioritas(nama, jarak, antrian);
        }
        SimpulAntrianPrioritas saatIni = antrian;
        while (saatIni.next != null && saatIni.next.jarak <= jarak) {
            saatIni = saatIni.next;
        }
        saatIni.next = new SimpulAntrianPrioritas(nama, jarak, saatIni.next);
        return antrian;
    }

    private Jalur bangunJalur(String tujuan, EntriStr sebelumnya, String mulai, int jarak) {
        SimpulTumpukan tumpukanJalur = null;
        String saatIni = tujuan;
        while (saatIni != null && !saatIni.equals(mulai)) {
            tumpukanJalur = new SimpulTumpukan(saatIni, tumpukanJalur);
            saatIni = dapatkanSebelumnya(sebelumnya, saatIni);
        }
        if (saatIni == null) return null;
        StringBuilder rute = new StringBuilder(mulai);
        while (tumpukanJalur != null) {
            rute.append(" => ").append(tumpukanJalur.data);
            tumpukanJalur = tumpukanJalur.next;
        }
        return new Jalur(rute.toString(), jarak);
    }

    private String dapatkanSebelumnya(EntriStr sebelumnya, String kunci) {
        EntriStr saatIni = sebelumnya;
        while (saatIni != null) {
            if (saatIni.kunci.equals(kunci)) return saatIni.nilai;
            saatIni = saatIni.next;
        }
        return null;
    }

    private SimpulJalur sisipkanJalurTerurut(SimpulJalur jalurTerurut, Jalur jalurBaru) {
        if (jalurTerurut == null || jalurBaru.jarak < jalurTerurut.data.jarak) {
            return new SimpulJalur(jalurBaru, jalurTerurut);
        }
        SimpulJalur saatIni = jalurTerurut;
        while (saatIni.next != null && saatIni.next.data.jarak <= jalurBaru.jarak) {
            saatIni = saatIni.next;
        }
        saatIni.next = new SimpulJalur(jalurBaru, saatIni.next);
        return jalurTerurut;
    }

    public void tampilkanJalur(String mulai) {
        EntriInt jarak = null;
        EntriStr sebelumnya = null;
        SimpulAntrianPrioritas antrianPrioritas = null;
        SimpulSet dikunjungi = null;

        jarak = new EntriInt(mulai, 0, jarak);
        antrianPrioritas = new SimpulAntrianPrioritas(mulai, 0, null);

        while (antrianPrioritas != null) {
            SimpulAntrianPrioritas simpulSaatIni = antrianPrioritas;
            antrianPrioritas = antrianPrioritas.next;
            String namaSaatIni = simpulSaatIni.nama;

            if (berisiDalamSet(dikunjungi, namaSaatIni)) continue;
            dikunjungi = new SimpulSet(namaSaatIni, dikunjungi);

            DaftarGaris garis = dapatkanDaftarGaris(namaSaatIni);
            if (garis != null) {
                NodeGaris nodeGaris = garis.head;
                while (nodeGaris != null) {
                    Garis garisSaatIni = nodeGaris.data;
                    int jarakBaru = dapatkanJarak(jarak, namaSaatIni) + garisSaatIni.bobot;
                    int jarakAda = dapatkanJarak(jarak, garisSaatIni.tujuan);
                    if (jarakBaru < jarakAda) {
                        jarak = masukkanJarak(jarak, garisSaatIni.tujuan, jarakBaru);
                        sebelumnya = masukkanSebelumnya(sebelumnya, garisSaatIni.tujuan, namaSaatIni);
                        antrianPrioritas = tambahkanKeAntrianPrioritas(antrianPrioritas, garisSaatIni.tujuan, jarakBaru);
                    }
                    nodeGaris = nodeGaris.next;
                }
            }
        }

        SimpulJalur semuaJalur = null;
        EntriInt entriJarak = jarak;
        while (entriJarak != null) {
            String tujuan = entriJarak.kunci;
            if (!tujuan.equals(mulai)) {
                Jalur jalur = bangunJalur(tujuan, sebelumnya, mulai, entriJarak.nilai);
                if (jalur != null) {
                    semuaJalur = new SimpulJalur(jalur, semuaJalur);
                }
            }
            entriJarak = entriJarak.next;
        }
        SimpulJalur jalurTerurut = null;
        SimpulJalur nodeJalur = semuaJalur;
        while (nodeJalur != null) {
            jalurTerurut = sisipkanJalurTerurut(jalurTerurut, nodeJalur.data);
            nodeJalur = nodeJalur.next;
        }
        System.out.println(mulai);
        SimpulJalur simpulJalurSaatIni = jalurTerurut;
        while (simpulJalurSaatIni != null) {
            System.out.println(simpulJalurSaatIni.data.rute);
            simpulJalurSaatIni = simpulJalurSaatIni.next;
        }
        System.out.println();
    }

    public void tambahGaris(String sumber, String tujuan, int bobot) {
        DaftarGaris garis = dapatkanDaftarGaris(sumber);
        if (garis == null) {
            garis = new DaftarGaris();
            hubungannya = new SimpulGaris(sumber, garis, hubungannya);
        }
        garis.tambah(new Garis(tujuan, bobot));
    }
}