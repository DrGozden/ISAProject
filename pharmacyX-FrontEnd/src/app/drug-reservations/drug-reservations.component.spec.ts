import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DrugReservationsComponent } from './drug-reservations.component';

describe('DrugReservationsComponent', () => {
  let component: DrugReservationsComponent;
  let fixture: ComponentFixture<DrugReservationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DrugReservationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DrugReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
