import React, { useState } from 'react';
import { Heart, Clock, Users, Edit2, Trash2 } from 'lucide-react';
import { useAuth } from '../context/AuthContext';
import { useRecipes } from '../context/RecipeContext';
import { Link } from 'react-router-dom';
import { AddRecipeModal } from './modals/AddRecipeModal';
import noImage from '../assets/no-image.jpg';

export function RecipeCard({ recipe, onLike }) {
    const { user } = useAuth();
    const { deleteRecipe, updateRecipe } = useRecipes();
    const [isEditModalOpen, setIsEditModalOpen] = useState(false);
    const [imgSrc, setImgSrc] = useState(recipe.image);

    // Check if current user is the author
    const isOwner = user && (recipe.author === user.name || recipe.author === 'You');

    const handleDelete = (e) => {
        e.preventDefault();
        e.stopPropagation();
        if (window.confirm('Are you sure you want to delete this recipe?')) {
            deleteRecipe(recipe.id);
        }
    };

    const handleEdit = (e) => {
        e.preventDefault();
        e.stopPropagation();
        setIsEditModalOpen(true);
    };

    const handleUpdate = (updatedData) => {
        updateRecipe(recipe.id, updatedData);
    };

    return (
        <>
            <div className="card h-100 border-0 shadow-sm animate-fade-in overflow-hidden">
                <div className="position-relative">
                    <Link to={`/recipe/${recipe.id}`} className="text-decoration-none">
                        <img
                            src={imgSrc || noImage}
                            onError={() => setImgSrc(noImage)}
                            alt={recipe.title}
                            className="card-img-top object-fit-cover"
                            style={{ height: '200px' }}
                        />
                    </Link>
                    <div className="position-absolute top-0 end-0 p-2">
                        <span className="badge bg-white text-primary shadow-sm rounded-pill px-3">
                            {recipe.category}
                        </span>
                    </div>
                </div>

                <div className="card-body">
                    <Link to={`/recipe/${recipe.id}`} className="text-decoration-none text-reset">
                        <h3 className="card-title h5 fw-bold mb-2 hover-text-primary transition-colors">{recipe.title}</h3>
                    </Link>
                    <p className="card-text text-muted small line-clamp-2">
                        {recipe.description}
                    </p>
                </div>

                <div className="card-footer bg-transparent border-0 pt-0 pb-3">
                    <div className="d-flex align-items-center justify-content-between">
                        <div className="d-flex gap-3 text-muted small">
                            <span className="d-flex align-items-center gap-1">
                                <Clock size={16} /> {recipe.time}
                            </span>
                            <span className="d-flex align-items-center gap-1">
                                <Users size={16} /> {recipe.servings}
                            </span>
                        </div>

                        <div className="d-flex gap-2">
                            {isOwner && (
                                <>
                                    <button
                                        onClick={handleEdit}
                                        className="btn btn-sm btn-outline-secondary rounded-circle p-2 d-flex align-items-center justify-content-center"
                                        style={{ width: '32px', height: '32px' }}
                                        title="Edit Recipe"
                                    >
                                        <Edit2 size={14} />
                                    </button>
                                    <button
                                        onClick={handleDelete}
                                        className="btn btn-sm btn-outline-danger rounded-circle p-2 d-flex align-items-center justify-content-center"
                                        style={{ width: '32px', height: '32px' }}
                                        title="Delete Recipe"
                                    >
                                        <Trash2 size={14} />
                                    </button>
                                </>
                            )}
                            <button
                                onClick={() => onLike(recipe.id)}
                                className={`btn p-2 d-flex align-items-center gap-1 border-0 rounded-pill ${recipe.isLiked ? 'text-primary bg-primary-subtle' : 'text-muted hover-bg-light'}`}
                                style={{ transition: 'all 0.2s' }}
                            >
                                <Heart size={20} fill={recipe.isLiked ? "currentColor" : "none"} />
                                <span className="fw-bold small">{recipe.likes}</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            {isEditModalOpen && (
                <AddRecipeModal
                    onClose={() => setIsEditModalOpen(false)}
                    onAdd={handleUpdate}
                    initialData={recipe}
                />
            )}
        </>
    );
}
