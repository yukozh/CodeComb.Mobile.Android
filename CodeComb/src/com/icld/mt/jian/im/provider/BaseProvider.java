package com.icld.mt.jian.im.provider;

import java.util.Collection;

import android.util.Log;

import com.codecomb.utils.DB4OUtils;
import com.codecomb.utils.DB4OUtils.Match;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class BaseProvider<T extends BaseEntry> {

	private static final String TAG = "BaseProvider";

	public void save(T obj) {
		if (obj == null) {
			return;
		}

		synchronized (DB4OUtils.class) {

			ObjectContainer oc = Db4oEmbedded.openFile(DB4OUtils
					.getDataBasePath());
			try {

				oc.store(obj);
				oc.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				oc.close();
			}
		}
	}
	
	public void saveOrUpdateAll(Collection<T> col) throws Exception
	{
		saveOrUpdateAll(col, new Match<T>() {

			@Override
			public boolean match(T oldObj, T newObj) {
				return oldObj.getId().equals(newObj.getId());
			}
		});
	}

	public void saveOrUpdateAll(Collection<T> col, final Match<T> match)
			throws Exception {
		if (col == null || col.size() == 0) {
			return;
		}

		synchronized (DB4OUtils.class) {

			ObjectContainer oc = Db4oEmbedded.openFile(DB4OUtils
					.getDataBasePath());

			try {
				for (final T obj : col) {

					ObjectSet<T> os = oc.query(new Predicate<T>() {

						/**
					 * 
					 */
						private static final long serialVersionUID = 2793555621552211152L;

						@Override
						public boolean match(T t) {
							// TODO Auto-generated method stub
							return match.match(t, obj);
						}
					});

					if (os.size() > 0) {
						T old = os.get(0);
						DB4OUtils.copyFields(obj, old);

						oc.store(old);

					} else {
						oc.store(obj);
					}
					
				}

				oc.commit();
			} catch (Exception e) {
				oc.rollback();
				throw e;

			} finally {

				oc.close();
			}

		}
	}

	public void saveOrUpdate(T obj) {
		saveOrUpdate(obj, new Match<T>() {

			@Override
			public boolean match(T oldObj, T newObj) {
				// TODO Auto-generated method stub
				return oldObj.getId().equals(newObj.getId());
			}
		});
	}

	public void saveOrUpdate(final T obj, final Match<T> match) {
		if (obj == null) {
			return;
		}

		synchronized (DB4OUtils.class) {

			ObjectContainer oc = Db4oEmbedded.openFile(DB4OUtils
					.getDataBasePath());
			try {

				ObjectSet<T> os = oc.query(new Predicate<T>() {

					/**
				 * 
				 */
					private static final long serialVersionUID = 2793555621552211152L;

					@Override
					public boolean match(T t) {
						// TODO Auto-generated method stub
						return match.match(t, obj);
					}
				});

				if (os.size() > 0) {
					T old = os.get(0);
					DB4OUtils.copyFields(obj, old);

					oc.store(old);


				} else {
					
					oc.store(obj);

				}
				
				oc.commit();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				
				oc.close();
			}
		}
	}

	public void delete(final T obj) {
		if (obj == null) {
			return;
		}

		synchronized (DB4OUtils.class) {

			ObjectContainer oc = Db4oEmbedded.openFile(DB4OUtils
					.getDataBasePath());

			try {

				ObjectSet<T> os = oc.query(new Predicate<T>() {

					/**
				 * 
				 */
					private static final long serialVersionUID = -7204009222312993206L;

					@Override
					public boolean match(T t) {
						// TODO Auto-generated method stub
						return t.getId() != null
								&& t.getId().equals(obj.getId());
					}
				});

				for (T t : os) {
					oc.delete(t);
				}
			} catch (Exception e) {

				Log.e(TAG, e.getMessage(), e);
			} finally {
				oc.close();
			}
		}
	}

	public T get(final Object id) {

		T result = null;

		synchronized (DB4OUtils.class) {

			ObjectContainer oc = Db4oEmbedded.openFile(DB4OUtils
					.getDataBasePath());

			try {
				ObjectSet<T> os = oc.query(new Predicate<T>() {

					/**
				 * 
				 */
					private static final long serialVersionUID = 7212666772385276584L;

					@Override
					public boolean match(T t) {
						// TODO Auto-generated method stub
						return t.getId() != null && t.getId().equals(id);
					}
				});

				if (os.size() > 0) {
					result = os.get(0);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {

				oc.close();
			}
		}

		return result;
	}

}
