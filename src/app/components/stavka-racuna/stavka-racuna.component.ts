import { Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Proizvod } from 'src/app/models/proizvod';
import { Racun } from 'src/app/models/racun';
import { StavkaRacuna } from 'src/app/models/stavka-racuna';
import { StavkaRacunaService } from 'src/app/services/stavka-racuna.service';
import { StavkaRacunaDialogComponent } from '../dialogs/stavka-racuna-dialog/stavka-racuna-dialog.component';

@Component({
  selector: 'app-stavka-racuna',
  templateUrl: './stavka-racuna.component.html',
  styleUrls: ['./stavka-racuna.component.css']
})
export class StavkaRacunaComponent implements OnInit, OnDestroy, OnChanges {

  displayedColumns = ['id', 'redniBroj', 'kolicina', 'jedinicaMere', 'cena', 'racun', 'proizvod', 'actions'];
  dataSource: MatTableDataSource<StavkaRacuna>;
  @Input() selektovanRacun: Racun;
  subscription: Subscription;

  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;

  constructor(private stavkaRacunaService: StavkaRacunaService,
              private dialog: MatDialog) { }

  ngOnChanges(): void {
    if(this.selektovanRacun.id) {
      this.loadData();
    }
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    //this.loadData();
  }

  loadData() {
    this.subscription = this.stavkaRacunaService.getStavkeZaRacun(this.selektovanRacun.id).subscribe(
      data => {
        this.dataSource = new MatTableDataSource(data);

        // pretraga po nazivu ugnježdenog objekta
        this.dataSource.filterPredicate = (data, filter: string) => {
          const accumulator = (currentTerm, key) => {
            return key === 'proizvod' ? currentTerm + data.proizvod.naziv : currentTerm + data[key];
          };
          const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
          const transformedFilter = filter.trim().toLowerCase();
          return dataStr.indexOf(transformedFilter) !== -1;
        };

        // sortiranje po nazivu ugnježdenog objekta
        this.dataSource.sortingDataAccessor = (data, property) => {
          switch (property) {
            case 'proizvod': return data.proizvod.naziv.toLocaleLowerCase();
            default: return data[property];
          }
        };

        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
    }
  }

  openDialog(flag: number, id?:number, redniBroj?:number, kolicina?: number, jedinicaMere?: string, cena?: number,
    racun?: Racun, proizvod?: Proizvod) {
      const dialogRef = this.dialog.open(StavkaRacunaDialogComponent,
        { data: {id, redniBroj, kolicina, jedinicaMere, cena, racun, proizvod}
      });
      dialogRef.componentInstance.flag = flag;
      if(flag == 1) {
        dialogRef.componentInstance.data.racun = this.selektovanRacun;
      }
      
      dialogRef.afterClosed().subscribe( res => {
        if(res == 1) {
          this.loadData();
        } 
      })
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();//odstraniti prazne karaktere
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }

}
