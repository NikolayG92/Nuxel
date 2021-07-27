import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SortingAdComponent } from './sorting-ad.component';

describe('SortingAdComponent', () => {
  let component: SortingAdComponent;
  let fixture: ComponentFixture<SortingAdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SortingAdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SortingAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
