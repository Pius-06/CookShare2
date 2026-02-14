import React, { useState, useEffect } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import { useRecipes } from '../context/RecipeContext';
import { Clock, Users, Heart, ArrowLeft, Share2, ChefHat } from 'lucide-react';
import noImage from '../assets/no-image.jpg';

export function RecipeDetail() {
    const { id } = useParams();
    const navigate = useNavigate();
    const { recipes, toggleLike } = useRecipes();
    const [imgSrc, setImgSrc] = useState(null);

    const recipe = recipes.find(r => r.id === parseInt(id));

    useEffect(() => {
        if (recipe) {
            setImgSrc(recipe.image);
        }
    }, [recipe]);

    if (!recipe) {
        return (
            <div className="container py-5 text-center animate-fade-in">
                <h2 className="display-6 fw-bold">Recipe not found</h2>
                <button onClick={() => navigate('/recipes')} className="btn btn-primary mt-3">
                    Back to Recipes
                </button>
            </div>
        );
    }

    return (
        <div className="container py-5 animate-fade-in">
            <button
                onClick={() => navigate(-1)}
                className="btn btn-link text-decoration-none text-muted mb-4 p-0 d-flex align-items-center gap-2"
            >
                <ArrowLeft size={20} /> Back
            </button>

            <div className="row g-5">
                <div className="col-lg-6">
                    <div className="position-relative rounded-4 overflow-hidden shadow">
                        <img
                            src={imgSrc || noImage}
                            onError={() => setImgSrc(noImage)}
                            alt={recipe.title}
                            className="img-fluid w-100 object-fit-cover"
                            style={{ height: '400px' }}
                        />
                        <div className="position-absolute top-0 end-0 p-3">
                            <span className="badge bg-white text-primary shadow px-3 py-2 rounded-pill fs-6">
                                {recipe.category}
                            </span>
                        </div>
                    </div>
                </div>

                <div className="col-lg-6">
                    <div className="d-flex align-items-center gap-2 mb-2">
                        <span className={`badge ${recipe.difficulty === 'Easy' ? 'text-bg-success' : recipe.difficulty === 'Medium' ? 'text-bg-warning' : 'text-bg-danger'} rounded-pill`}>
                            {recipe.difficulty || 'Medium'}
                        </span>
                        <span className="text-muted small">
                            by <Link to={`/user/${recipe.author}`} className="text-decoration-none text-primary fw-semibold">{recipe.author}</Link>
                        </span>
                    </div>

                    <h1 className="display-5 fw-bold mb-3">{recipe.title}</h1>

                    {recipe.introduction && (
                        <p className="lead fst-italic text-muted mb-4 border-start border-4 border-primary ps-3">
                            "{recipe.introduction}"
                        </p>
                    )}

                    <p className="mb-4">{recipe.description}</p>

                    <div className="d-flex flex-wrap gap-4 mb-4 pb-4 border-bottom">
                        <div className="d-flex align-items-center gap-2 text-primary bg-primary-subtle px-3 py-2 rounded-pill">
                            <Clock size={20} />
                            <span className="fw-semibold">{recipe.time}</span>
                        </div>
                        <div className="d-flex align-items-center gap-2 text-primary bg-primary-subtle px-3 py-2 rounded-pill">
                            <Users size={20} />
                            <span className="fw-semibold">{recipe.servings}</span>
                        </div>
                        <div className="d-flex align-items-center gap-2 text-primary bg-primary-subtle px-3 py-2 rounded-pill">
                            <Heart size={20} fill={recipe.isLiked ? "currentColor" : "none"} />
                            <span className="fw-semibold">{recipe.likes} Likes</span>
                        </div>
                    </div>

                    <div className="d-flex gap-2 mb-5">
                        <button
                            onClick={() => toggleLike(recipe.id)}
                            className={`btn flex-grow-1 d-flex align-items-center justify-content-center gap-2 py-2 ${recipe.isLiked ? 'btn-primary' : 'btn-outline-primary'}`}
                        >
                            <Heart size={20} fill={recipe.isLiked ? "currentColor" : "none"} />
                            {recipe.isLiked ? 'Liked' : 'Like Recipe'}
                        </button>
                        <button className="btn btn-outline-secondary px-3">
                            <Share2 size={20} />
                        </button>
                    </div>

                    <div className="mb-4">
                        <h3 className="h4 fw-bold mb-3 d-flex align-items-center gap-2">
                            <ChefHat size={24} className="text-primary" /> Ingredients
                        </h3>
                        <ul className="list-group list-group-flush rounded-3 border">
                            <li className="list-group-item">2 cups All-purpose flour</li>
                            <li className="list-group-item">1 tsp Salt</li>
                            <li className="list-group-item">3 large Eggs</li>
                            <li className="list-group-item">1/2 cup Milk</li>
                            <li className="list-group-item">2 tbsp Olive Oil</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
}
