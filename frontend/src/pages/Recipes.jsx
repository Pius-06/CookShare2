import React, { useState } from 'react';
import { useRecipes } from '../context/RecipeContext';
import { RecipeCard } from '../components/RecipeCard';
import { AddRecipeModal } from '../components/modals/AddRecipeModal';
import { Plus, Search } from 'lucide-react';

export function Recipes() {
  const { recipes, toggleLike, addRecipe } = useRecipes();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('All');

  const categories = ['All', ...new Set(recipes.map(r => r.category))];

  const filteredRecipes = recipes.filter(r => {
    const matchesSearch = r.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
      r.category.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesCategory = selectedCategory === 'All' || r.category === selectedCategory;
    return matchesSearch && matchesCategory;
  });

  return (<>
    <div className="container py-4 animate-fade-in">
      <div className="d-flex flex-column gap-3 mb-4">
        <div className="d-flex justify-content-between align-items-center">
          <h2 className="mb-0 fw-bold">All Recipes</h2>
        </div>

        <div className="d-flex flex-column flex-md-row gap-3">
          <div className="position-relative flex-grow-1">
            <input
              className="form-control ps-5"
              placeholder="Search recipes..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
            <Search size={18} className="position-absolute top-50 translate-middle-y text-muted" style={{ left: '1rem' }} />
          </div>

          <select
            className="form-select w-auto"
            value={selectedCategory}
            onChange={(e) => setSelectedCategory(e.target.value)}
          >
            {categories.map(cat => (
              <option key={cat} value={cat}>{cat}</option>
            ))}
          </select>

          <button
            className="btn btn-primary d-flex align-items-center gap-2"
            onClick={() => setIsModalOpen(true)}
          >
            <Plus size={20} /> Add Recipe
          </button>
        </div>
      </div>

      <div className="row g-4">
        {filteredRecipes.map(recipe => (
          <div className="col-12 col-md-6 col-lg-4" key={recipe.id}>
            <RecipeCard
              recipe={recipe}
              onLike={() => toggleLike(recipe.id)}
            />
          </div>
        ))}
      </div>


    </div>
    {isModalOpen && (
      <AddRecipeModal
        onClose={() => setIsModalOpen(false)}
        onAdd={addRecipe}
      />
    )}
  </>
  );
}
