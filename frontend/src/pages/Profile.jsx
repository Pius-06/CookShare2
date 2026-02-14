import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { useRecipes } from '../context/RecipeContext';
import { RecipeCard } from '../components/RecipeCard';
import { AddRecipeModal } from '../components/modals/AddRecipeModal';
import { EditProfileModal } from '../components/modals/EditProfileModal';
import { Navigate } from 'react-router-dom';
import { User, Settings, LogOut, ChefHat, Plus, Search } from 'lucide-react';

export function Profile() {
    const { user, logout } = useAuth();
    const { recipes, toggleLike, addRecipe } = useRecipes();
    const [activeTab, setActiveTab] = useState('my-recipes');
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isEditProfileOpen, setIsEditProfileOpen] = useState(false);
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedCategory, setSelectedCategory] = useState('All');

    if (!user) return <Navigate to="/login" />;

    // Filter Logic
    const myRecipes = recipes.filter(r => r.author === user.name || r.author === 'You');
    const likedRecipes = recipes.filter(r => r.isLiked);

    const sourceRecipes = activeTab === 'my-recipes' ? myRecipes : likedRecipes;
    const categories = ['All', ...new Set(sourceRecipes.map(r => r.category))];

    const displayedRecipes = sourceRecipes.filter(r => {
        const matchesSearch = r.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
            r.category.toLowerCase().includes(searchTerm.toLowerCase());
        const matchesCategory = selectedCategory === 'All' || r.category === selectedCategory;
        return matchesSearch && matchesCategory;
    });

    return (
        <div className="container py-5 animate-fade-in">
            <div className="row justify-content-center mb-5">
                <div className="col-lg-8 text-center">
                    <div className="mb-4">
                        <div className="bg-primary-subtle rounded-circle d-inline-flex align-items-center justify-content-center text-primary mb-3" style={{ width: '100px', height: '100px' }}>
                            <User size={48} />
                        </div>
                        <h1 className="fw-bold mb-1">{user.name}</h1>
                        <p className="text-muted mb-3">{user.email}</p>
                        {user.bio && (
                            <p className="text-muted fst-italic mx-auto" style={{ maxWidth: '500px' }}>
                                "{user.bio}"
                            </p>
                        )}
                    </div>

                    <div className="d-flex justify-content-center gap-2 mb-4">
                        <button
                            onClick={() => setIsEditProfileOpen(true)}
                            className="btn btn-outline-secondary d-flex align-items-center gap-2"
                        >
                            <Settings size={18} /> Edit Profile
                        </button>
                        <button onClick={logout} className="btn btn-danger d-flex align-items-center gap-2">
                            <LogOut size={18} /> Logout
                        </button>
                    </div>

                    <div className="d-flex justify-content-center gap-5 text-center px-4 py-3 bg-body-tertiary rounded-4 shadow-sm mx-auto" style={{ maxWidth: '400px' }}>
                        <div>
                            <div className="h4 fw-bold mb-0 text-primary">{myRecipes.length}</div>
                            <div className="small text-muted">Recipes</div>
                        </div>
                        <div className="vr"></div>
                        <div>
                            <div className="h4 fw-bold mb-0 text-primary">{likedRecipes.length}</div>
                            <div className="small text-muted">Liked</div>
                        </div>
                        <div className="vr"></div>
                        <div>
                            <div className="h4 fw-bold mb-0 text-primary">12</div>
                            <div className="small text-muted">Followers</div>
                        </div>
                    </div>
                </div>
            </div>

            <ul className="nav nav-pills justify-content-center mb-4 gap-2">
                <li className="nav-item">
                    <button
                        className={`nav-link px-4 ${activeTab === 'my-recipes' ? 'active' : ''}`}
                        onClick={() => {
                            setActiveTab('my-recipes');
                            setSearchTerm('');
                            setSelectedCategory('All');
                        }}
                    >
                        My Recipes
                    </button>
                </li>
                <li className="nav-item">
                    <button
                        className={`nav-link px-4 ${activeTab === 'liked' ? 'active' : ''}`}
                        onClick={() => {
                            setActiveTab('liked');
                            setSearchTerm('');
                            setSelectedCategory('All');
                        }}
                    >
                        Liked Dishes
                    </button>
                </li>
            </ul>

            {/* Search & Filter Section */}
            <div className="d-flex flex-column flex-md-row gap-3 mb-4 justify-content-center align-items-center">
                <div className="position-relative" style={{ width: '100%', maxWidth: '300px' }}>
                    <input
                        className="form-control ps-5"
                        placeholder={`Search ${activeTab === 'my-recipes' ? 'your' : 'liked'} recipes...`}
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

                {activeTab === 'my-recipes' && (
                    <button
                        className="btn btn-primary d-flex align-items-center gap-2"
                        onClick={() => setIsModalOpen(true)}
                    >
                        <Plus size={20} /> Add Recipe
                    </button>
                )}
            </div>

            {displayedRecipes.length > 0 ? (
                <div className="row g-4">
                    {displayedRecipes.map(recipe => (
                        <div className="col-12 col-md-6 col-lg-4" key={recipe.id}>
                            <RecipeCard
                                recipe={recipe}
                                onLike={() => toggleLike(recipe.id)}
                            />
                        </div>
                    ))}
                </div>
            ) : (
                <div className="text-center py-5 text-muted bg-body-tertiary rounded-4">
                    <ChefHat size={48} className="mb-3 opacity-25" />
                    <p className="h5">No recipes found matching your criteria.</p>
                </div>
            )}

            {isModalOpen && (
                <AddRecipeModal
                    onClose={() => setIsModalOpen(false)}
                    onAdd={addRecipe}
                />
            )}

            {isEditProfileOpen && (
                <EditProfileModal
                    onClose={() => setIsEditProfileOpen(false)}
                />
            )}
        </div>
    );
}
