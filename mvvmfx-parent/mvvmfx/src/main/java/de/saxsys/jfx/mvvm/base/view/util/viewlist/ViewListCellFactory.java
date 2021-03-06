/*******************************************************************************
 * Copyright 2013 Alexander Casall
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.saxsys.jfx.mvvm.base.view.util.viewlist;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import de.saxsys.jfx.mvvm.base.view.View;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;
import de.saxsys.jfx.mvvm.viewloader.ViewTuple;

/**
 * Factory which provides the mapping between some data to a {@link ViewTuple}.
 * You can use this, for transforming lists of technical IDs in the
 * {@link ViewModel} to a {@link ListView} which displays {@link View} by a
 * given {@link ViewTuple}.
 * 
 * @author sialcasa
 * 
 * @param <T>
 *            Datatype which is the mapping source
 */
public abstract class ViewListCellFactory<T> implements
		Callback<ListView<T>, ListCell<T>>, ViewTupleMapper<T> {

	@Override
	public abstract ViewTuple<? extends ViewModel> map(T element);

	@Override
	public ViewListCell<T> call(ListView<T> element) {
		return new ViewListCell<T>() {
			@Override
			public ViewTuple<? extends ViewModel> map(T element) {
				return ViewListCellFactory.this.map(element);
			}
		};
	}
}
