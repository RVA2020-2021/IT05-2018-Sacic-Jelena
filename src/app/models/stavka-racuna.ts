import { Proizvod } from "./proizvod";
import { Racun } from "./racun";

export class StavkaRacuna {
    id: number;
    cena: number;
    jedinicaMere: string;
    kolicina: number;
    redniBroj: number;
    proizvod: Proizvod;
    racun: Racun;
}