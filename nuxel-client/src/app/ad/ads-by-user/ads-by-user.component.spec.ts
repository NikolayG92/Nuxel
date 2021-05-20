import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdsByUserComponent } from './ads-by-user.component';

describe('AdsByUserComponent', () => {
  let component: AdsByUserComponent;
  let fixture: ComponentFixture<AdsByUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdsByUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdsByUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
