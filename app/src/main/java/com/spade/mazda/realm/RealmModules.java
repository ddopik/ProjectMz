package com.spade.mazda.realm;


import com.spade.mazda.ui.authentication.model.User;
import com.spade.mazda.ui.cars.model.realm_models.CarModel;
import com.spade.mazda.ui.cars.model.realm_models.CarYear;
import com.spade.mazda.ui.cars.model.realm_models.ModelTrim;
import com.spade.mazda.ui.cars.model.realm_models.TrimColor;

import io.realm.annotations.RealmModule;

/**
 * Created by Ayman Abouzeid on 6/13/17.
 */

@RealmModule(classes = {User.class, CarModel.class, CarYear.class, ModelTrim.class, TrimColor.class})
public class RealmModules {
}
