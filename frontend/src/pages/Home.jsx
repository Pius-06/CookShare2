import React from 'react';
import { useRecipes } from '../context/RecipeContext';
import { useAuth } from '../context/AuthContext';
import { RecipeCard } from '../components/RecipeCard';
import { Link } from 'react-router-dom';
import { ArrowRight } from 'lucide-react';

export function Home() {
    const { recipes, toggleLike } = useRecipes();
    const { user } = useAuth();

    // Get top 3 recipes by likes
    const topRecipes = [...recipes].sort((a, b) => b.likes - a.likes).slice(0, 3);

    return (
        <div className="animate-fade-in">
            <section className="bg-body-tertiary py-5 mb-5 text-center rounded-bottom-3 bg-gradient" style={{ background: 'linear-gradient(135deg, var(--primary-100) 0%, var(--bs-body-bg) 100%)' }}>
                <div className="container py-5">
                    <h1 className="display-4 fw-bold mb-3 text-primary">
                        Share Your Culinary Masterpieces
                    </h1>
                    <p className="lead text-muted mb-4 mx-auto" style={{ maxWidth: '600px' }}>
                        Join our community of food lovers. Discover, share, and like the best homemade recipes.
                    </p>
                    <Link to="/recipes" className="btn btn-primary btn-lg d-inline-flex align-items-center gap-2 px-4">
                        Explore Recipes <ArrowRight size={20} />
                    </Link>
                </div>
            </section>

            <section className="container mb-5">
                <h2 className="mb-4 h3 fw-bold">🔥 Trending Now</h2>
                <div className="row g-4">
                    {topRecipes.map(recipe => (
                        <div className="col-12 col-md-6 col-lg-4" key={recipe.id}>
                            <RecipeCard
                                recipe={recipe}
                                onLike={() => toggleLike(recipe.id)}
                            />
                        </div>
                    ))}
                </div>
            </section>

            {/* CTA Section - Only show if user is not logged in */}
            {!user && (
                <section className="container mb-5">
                    <div className="card p-5 text-center shadow-sm border-0">
                        <h2 className="mb-3">Become part of the Community!</h2>
                        <p className="text-muted mb-4">Create an account and share your recipes.</p>
                        <div>
                            <Link to="/register" className="btn btn-primary btn-lg">
                                Register now
                            </Link>
                        </div>
                    </div>
                </section>
            )}
        </div>
    );
}
