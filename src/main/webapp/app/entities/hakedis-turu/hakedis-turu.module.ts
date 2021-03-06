import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { DefterTakipSharedModule } from 'app/shared';
import {
    HakedisTuruComponent,
    HakedisTuruDetailComponent,
    HakedisTuruUpdateComponent,
    HakedisTuruDeletePopupComponent,
    HakedisTuruDeleteDialogComponent,
    hakedisTuruRoute,
    hakedisTuruPopupRoute
} from './';

const ENTITY_STATES = [...hakedisTuruRoute, ...hakedisTuruPopupRoute];

@NgModule({
    imports: [DefterTakipSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        HakedisTuruComponent,
        HakedisTuruDetailComponent,
        HakedisTuruUpdateComponent,
        HakedisTuruDeleteDialogComponent,
        HakedisTuruDeletePopupComponent
    ],
    entryComponents: [HakedisTuruComponent, HakedisTuruUpdateComponent, HakedisTuruDeleteDialogComponent, HakedisTuruDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DefterTakipHakedisTuruModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
