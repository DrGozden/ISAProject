import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllPharmacistComponent } from './all-pharmacist.component';

describe('AllPharmacistComponent', () => {
  let component: AllPharmacistComponent;
  let fixture: ComponentFixture<AllPharmacistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllPharmacistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllPharmacistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
