import { useEffect, useState } from 'react';
import axios from 'axios';

function App() {

  const [categories, setCategories] = useState([]);
  const [categoryName, setCategoryName] = useState('');
  const [description, setDescription] = useState('');
  const [editId, setEditId] = useState(null);

  const API = 'http://localhost:8080/api/categories';

  const fetchCategories = async () => {
    const response = await axios.get(API);
    setCategories(response.data);
  };

  useEffect(() => {
    fetchCategories();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const category = {
      categoryName,
      description,
      status: true
    };

    if (editId) {
      await axios.put(`${API}/${editId}`, category);
    } else {
      await axios.post(API, category);
    }

    setCategoryName('');
    setDescription('');
    setEditId(null);
    fetchCategories();
  };

  const handleEdit = (category) => {
    setEditId(category.categoryId);
    setCategoryName(category.categoryName);
    setDescription(category.description || '');
  };

  const handleDeactivate = async (id) => {
  if (window.confirm(
    'Before deactivating this category, please assign its products to another category. Do you want to continue?'
  )) {
    await axios.put(`${API}/${id}/deactivate`);
    fetchCategories();
  }
};

  return (
    <div className="container mt-5">

      <h2 className="text-center mb-4">
        Category Management
      </h2>

      <div className="card p-4 mb-4">

        <h4>{editId ? 'Update Category' : 'Create New Category'}</h4>

        <form onSubmit={handleSubmit}>

          <div className="mb-3">
            <label className="form-label">Category Name</label>
            <input
              type="text"
              className="form-control"
              value={categoryName}
              onChange={(e) => setCategoryName(e.target.value)}
              required
            />
          </div>

          <div className="mb-3">
            <label className="form-label">Description</label>
            <textarea
              className="form-control"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            />
          </div>

          <button type="submit" className="btn btn-primary">
            {editId ? 'Update Category' : 'Add Category'}
          </button>

        </form>
      </div>

      <h4>Category Dashboard</h4>

      <table className="table table-bordered table-striped mt-3">

        <thead className="table-dark">
          <tr>
            <th>ID</th>
            <th>Category Name</th>
            <th>Description</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {categories.map((category) => (
            <tr key={category.categoryId}>
              <td>{category.categoryId}</td>
              <td>{category.categoryName}</td>
              <td>{category.description}</td>
              <td>
                {category.status ? 'Active' : 'Inactive'}
              </td>
              <td>
                <button
                  className="btn btn-warning btn-sm me-2"
                  onClick={() => handleEdit(category)}
                  disabled={!category.status}
                >
                  Edit
                </button>

                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => handleDeactivate(category.categoryId)}
                  disabled={!category.status}
                >
                  Deactivate
                </button>
              </td>
            </tr>
          ))}
        </tbody>

      </table>

    </div>
  );
}

export default App;